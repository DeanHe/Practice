package dfs;

import java.util.*;

public class FileAccessDropbox {
    public boolean isAccessable(String root, String path, Map<String, List<String>> g, Set<String> access){
        dfs(root, g, access, false);
        return access.contains(path);
    }

    public void conciseAccess(String root, Map<String, List<String>> g, Set<String> access){
        dfs2(root, g, access, false);
    }

    public void conciseAccessWithNoInherit(String root, Map<String, List<String>> g, Set<String> access, Set<String> noInheritFolders){
        dfs3(root, g, access, noInheritFolders, false);
    }

    private void dfs(String path, Map<String, List<String>> g, Set<String> access, boolean isParentAccess){
        if(isParentAccess){
            access.add(path);
        }
        for(String child : g.getOrDefault(path, new ArrayList<>())){
            if(access.contains(path)){
                dfs(child, g, access, true);
            } else {
                dfs(child, g, access, false);
            }
        }
    }

    private void dfs2(String path, Map<String, List<String>> g, Set<String> access, boolean isParentAccess){
        if(isParentAccess){
            access.remove(path);
        }
        for(String child : g.getOrDefault(path, new ArrayList<>())){
            if(access.contains(path) || isParentAccess){
                dfs2(child, g, access, true);
            } else {
                dfs2(child, g, access, false);
            }
        }
    }

    private void dfs3(String path, Map<String, List<String>> g, Set<String> access, Set<String> noInheritFolders, boolean isParentAccess){
        if(isParentAccess && !noInheritFolders.contains(path)){
            access.remove(path);
        }
        for(String child : g.getOrDefault(path, new ArrayList<>())){
            if(access.contains(path) || isParentAccess){
                dfs2(child, g, access, true);
            } else {
                dfs2(child, g, access, false);
            }
        }
    }
}

