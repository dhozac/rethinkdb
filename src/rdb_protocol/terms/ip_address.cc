// Copyright 2010-2017 The Linux Foundation, all rights reserved

#include <string>
#include <sstream>
#include <cstring>
#include <cerrno>
#include <cstdint>
#include <arpa/inet.h>

#include "rdb_protocol/error.hpp"
#include "rdb_protocol/func.hpp"
#include "rdb_protocol/op.hpp"
#include "rdb_protocol/terms/terms.hpp"
#include "rdb_protocol/pseudo_ip_address.hpp"

namespace ql {

class ip_address_term_t : public op_term_t {
public:
    ip_address_term_t(compile_env_t *env, const raw_term_t &term)
        : op_term_t(env, term, argspec_t(1)) { }
private:
    scoped_ptr_t<val_t> eval_impl(scope_env_t *env, args_t *args, eval_flags_t) const {
        const std::string ip_address = args->arg(env, 0)->as_str().to_std();
        try {
            return new_val(pseudo::make_ip_address(ip_address));
        }
        catch (const std::invalid_argument& e) {
            rfail_target(args->arg(env, 0).get(), base_exc_t::LOGIC,
                         "Unreadable address \"%s\": %s", ip_address.c_str(),
                         std::strerror(errno));
        }
    }
    virtual const char *name() const { return "ip_address"; }
};

class ip_prefix_term_t : public op_term_t {
public:
    ip_prefix_term_t(compile_env_t *env, const raw_term_t &term)
        : op_term_t(env, term, argspec_t(1, 2)) { }
private:
    scoped_ptr_t<val_t> eval_impl(scope_env_t *env, args_t *args, eval_flags_t) const {
        std::string ip_prefix;
        std::string cidr;
        int length;
        if (args->num_args() == 2) {
            std::ostringstream cidr_ss;
            length = args->arg(env, 1)->as_num();
            ip_prefix = args->arg(env, 0)->as_str().to_std();
            cidr_ss << ip_prefix << "/" << length;
            cidr = cidr_ss.str();
        }
        else {
            cidr = args->arg(env, 0)->as_str().to_std();
            size_t separator = cidr.find("/");
            const std::string length_str = cidr.substr(separator + 1);
            ip_prefix = cidr.substr(0, separator);
            length = std::stoi(length_str);
        }
        try {
            return new_val(pseudo::make_ip_prefix(cidr, ip_prefix, length));
        }
        catch (const std::invalid_argument& e) {
            rfail_target(args->arg(env, 0).get(), base_exc_t::LOGIC,
                         "Unreadable prefix \"%s\": %s", ip_prefix.c_str(),
                         std::strerror(errno));
        }
    }
    virtual const char *name() const { return "ip_prefix"; }
};

class ip_prefix_contains_term_t : public op_term_t {
public:
    ip_prefix_contains_term_t(compile_env_t *env, const raw_term_t &term)
        : op_term_t(env, term, argspec_t(2)) { }
private:
    scoped_ptr_t<val_t> eval_impl(scope_env_t *env, args_t *args, eval_flags_t) const {
        return new_val_bool(pseudo::ip_prefix_contains(args->arg(env, 0)->as_datum(),
            args->arg(env, 1)->as_datum()));
    }
    virtual const char *name() const { return "ip_prefix_contains"; }
};

counted_t<term_t> make_ip_address_term(
        compile_env_t *env, const raw_term_t &term) {
    return make_counted<ip_address_term_t>(env, term);
}
counted_t<term_t> make_ip_prefix_term(
        compile_env_t *env, const raw_term_t &term) {
    return make_counted<ip_prefix_term_t>(env, term);
}
counted_t<term_t> make_ip_prefix_contains_term(
        compile_env_t *env, const raw_term_t &term) {
    return make_counted<ip_prefix_contains_term_t>(env, term);
}

} // namespace ql
