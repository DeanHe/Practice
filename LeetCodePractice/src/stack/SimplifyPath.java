package stack;

/*
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.



Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"

analysis:
The main idea is to push to the stack every valid file name (not in {"",".",".."}),
popping only if there's smth to pop and we met "..".
*/

import java.util.Arrays;
import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

public class SimplifyPath {
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        Set<String> skip = new HashSet<>(Arrays.asList(".", "..",""));
        for(String str : path.split("/")){
            if(str.equals("..") && !stack.isEmpty()){
                stack.pop();
            } else if(!skip.contains(str)){
                stack.push(str);
            }
        }
        while(!stack.isEmpty()){
            sb.insert(0, "/" + stack.pop());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

}
