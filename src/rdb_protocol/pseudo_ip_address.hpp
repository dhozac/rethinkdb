// Copyright 2010-2017 The Linux Foundation, all rights reserved.
#ifndef RDB_PROTOCOL_PSEUDO_IP_ADDRESS_HPP_
#define RDB_PROTOCOL_PSEUDO_IP_ADDRESS_HPP_

namespace ql {
namespace pseudo {
extern const char *const ip_address_string;
extern const char *const ip_prefix_string;

extern datum_t make_ip_address(std::string ip_address);
extern datum_t make_ip_prefix(const std::string cidr, const std::string ip_prefix, int length);
extern bool ip_prefix_contains(datum_t ip_prefix, datum_t ip_address);
};
};

#endif
