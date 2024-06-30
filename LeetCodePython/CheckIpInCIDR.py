"""
check if a given IP is inside the CIDR range

example:
ip: 10.0.0.4
cidr: 10.0.0.1/24
"""

class CheckIpInCIDR:
    def check(self, ip, cidr):
        ip_sections = ip.split('.')
        cidr_sections = cidr.split('/')
        cidr_ip = cidr_sections[0]
        cidr_prefix = int(cidr_sections[1])
        cidr_ip_sections = cidr_ip.split('.')

        def convert_ip_to_binary(arr):
            res = ''
            for sub in arr:
                bits = bin(int(sub))[2:]
                if len(bits) < 8:
                    bits = '0' * (8 - len(bits)) + bits
                res += bits
            return res

        ip_binary = convert_ip_to_binary(ip_sections)
        cidr_ip_binary = convert_ip_to_binary(cidr_ip_sections)
        print(ip_binary, cidr_ip_binary)
        ip_bitmask = ip_binary[:cidr_prefix]
        cidr_bitmask = cidr_ip_binary[:cidr_prefix]
        print(ip_bitmask, cidr_bitmask)
        return ip_bitmask == cidr_bitmask

# test
checkIpInCIDR = CheckIpInCIDR()
checkIpInCIDR.check('10.0.0.19','10.0.0.0/24')


