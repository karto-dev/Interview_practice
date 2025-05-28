package DataStructures.Graphs;

import java.util.*;

public class BFSDFS {
    public static void DFS(Map<Integer, List<Integer>> graph,Set<Integer> visited,int node){
        if(visited.contains(node)) return;
        visited.add(node);
        System.out.println(STR."\{node} \{visited}");
        for(int neighbour:graph.getOrDefault(node,new ArrayList<>())){
            DFS(graph,visited,neighbour);
        }
    }
    public static void BFS(Map<Integer, List<Integer>> graph,int start){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()){
            int node = queue.poll();
            System.out.println(STR."\{node} \{visited}");
            for(int neighbour:graph.getOrDefault(node,new ArrayList<>())){
                if(!visited.contains(neighbour)){
                    queue.offer(neighbour);
                    visited.add(neighbour);
                }
            }
        }

    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        graph.put(0,Arrays.asList(1,2));
        graph.put(1,Arrays.asList(0,2));
        graph.put(2,Arrays.asList(0,1,3));
        graph.put(3,Arrays.asList(2));
        System.out.println("DFS Traversal:");
        DFS(graph,visited,0);
        BFS(graph,0);
    }
}
