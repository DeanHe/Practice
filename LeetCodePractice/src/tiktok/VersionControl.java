package tiktok;
/*
In Bytedance, we publish countless new features on hundreds of different services every
day. Version control is a very important thing for different new features.

We must ensure that this new feature only works for users who have installed the specified
or newer version.

Now we need to design a version comparison algorithm to protect our system.
A version can be made up of major.minor.patch --{pre release} that they are split by dot,
where minor, patch and (pre release) are optional, in which pre release can be any word.
Like
1.0.0, 2.0, 3, 4.1.1-alpha
The comparison rules for versions are as follows We start with the major, then the minor,
then the patch. Design a algorithm to return if version1 is greater or equal to version2
1 means greater, 0 means equal, -1 means smaller When major, minor, and patch are equal, a pre release
version has lower precedence than a normal version

Example1
version1 = 1.0.1, version2 = 1.0.0
1

Example2
version1 = 1.0.1, version2 = 1
1

Example3
version1 = 1.01, version2 = 1. 001
0
Explanation: Ignoring leading zeroes, both "01" аnd
"
ѲѲ1" represents thе same integer "1".

Example4
version1 = 1.0.0
alpha, version2 = 1.0.0
-1
Explanation: When major, minor, and patch are equal, a prerelease version has lower precedence than a normal version
 */
public class VersionControl {
    public int compareVersion(String v1, String v2){
        Version version1 = parse(v1);
        Version version2 = parse(v2);
        return version1.compareTo(version2);
    }

    private Version parse(String s){
        Version res = new Version();
        String[] arr = s.split("-");
        if(arr.length > 1){
            res.preRelease = arr[1].trim();
        }
        String[] arr2 = arr[0].split("\\.");
        if(arr2.length > 0){
            res.major = Integer.parseInt(arr2[0].trim());
        }
        if(arr2.length > 1){
            res.minor = Integer.parseInt(arr2[1].trim());
        }
        if(arr2.length > 2){
            res.patch = Integer.parseInt(arr2[2].trim());
        }
        return res;
    }

    class Version implements Comparable<Version> {
        public int major, minor, patch;
        public String preRelease;

        @Override
        public int compareTo(Version o) {
            if(major > o.major){
                return 1;
            } else if(major < o.major){
                return -1;
            } else {
                if(minor > o.minor){
                    return 1;
                } else if(minor < o.minor){
                    return -1;
                } else {
                    if(patch > o.patch){
                        return 1;
                    } else if(patch < o.patch){
                        return -1;
                    }
                }
            }
            if(preRelease != null){
                if(o.preRelease == null){
                    return -1;
                }
            }
            return 0;
        }
    }
}
