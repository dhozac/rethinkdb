// Copyright 2010-2017 The Linux Foundation, all rights reserved
#include "rdb_protocol/datum.hpp"
#include "rdb_protocol/pseudo_ip_address.hpp"
#include "utils.hpp"

#include <stdexcept>
#include <string>
#include <cstring>
#include <cerrno>
#include <cstdint>
#include <arpa/inet.h>

namespace ql {
namespace pseudo {

const char *const ip_address_string = "IP_ADDRESS";
const char *const ip_prefix_string = "IP_PREFIX";
const char *const ipv4_key = "ipv4";
const char *const netmask_key = "netmask";
const char *const ip_address_key = "ip_address";
const char *const ip_prefix_key = "ip_prefix";

datum_t make_ip_address(std::string ip_address) {
    struct in_addr address_in_addr;
    if (inet_pton(AF_INET, ip_address.c_str(), &address_in_addr) != 1)
        throw std::invalid_argument("ip_address is invalid");
    std::map<datum_string_t, datum_t> map
        = { { datum_string_t(datum_t::reql_type_string), datum_t(ip_address_string) },
            { datum_string_t(ipv4_key), datum_t((double) address_in_addr.s_addr) },
            { datum_string_t(ip_address_key), datum_t(ip_address) } };
    return datum_t(std::move(map));
}

datum_t make_ip_prefix(const std::string cidr, const std::string ip_prefix, int length) {
    struct in_addr address_in_addr;
    if (inet_pton(AF_INET, ip_prefix.c_str(), &address_in_addr) != 1)
        throw std::invalid_argument("ip_prefix is invalid");
    uint32_t netmask = htonl(~((1 << (32 - length)) - 1));
    std::map<datum_string_t, datum_t> map
        = { { datum_string_t(datum_t::reql_type_string), datum_t(ip_prefix_string) },
            { datum_string_t(ipv4_key), datum_t((double) address_in_addr.s_addr) },
            { datum_string_t(netmask_key), datum_t((double) netmask) },
            { datum_string_t(ip_prefix_key), datum_t(cidr) } };
    return datum_t(std::move(map));
}

bool ip_prefix_contains(datum_t ip_prefix, datum_t ip_address) {
    uint32_t prefix = ip_prefix.get_field(ipv4_key).as_int();
    uint32_t netmask = ip_prefix.get_field(netmask_key).as_int();
    uint32_t address = ip_address.get_field(ipv4_key).as_int();
    return (prefix & netmask) == (address & netmask);
}

} // namespace pseudo
} // namespace ql
