package _10_graph;

import java.util.*;

public class ListGraph<V, E> implements Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();

    private Set<Edge<V, E>> edges = new HashSet<>();

    public ListGraph() { }

    private static class Vertex<V, E>{
        V value;
        Set<Edge<V,E>> inDegrees = new HashSet<>();

        Set<Edge<V,E>> outDegrees = new HashSet();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<V, E> vertex = (Vertex<V, E>) o;
            return Objects.equals(value, vertex.value) &&
                    Objects.equals(inDegrees, vertex.inDegrees) &&
                    Objects.equals(outDegrees, vertex.outDegrees);
        }

        @Override
        public int hashCode(){
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return this.value == null ? "null": this.value.toString();
        }
    }

    private static class Edge<V, E>{
        E weight;
        Vertex<V, E> from;
        Vertex<V, E> to;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
            weight = null; // 默认值
        }

        @Override
        public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<V, E> edge = (Edge<V, E>) o;
//            return
            return  Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode(){
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }


    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
//        1. 先判断from, to 是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        // 能来到这儿，说明一定可以保证有起点，有终点
        // 接下来，需要确定，起点终点间是否之前已经存在边，如果不存在，则新建一条；否则，更新weight
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

        if ( fromVertex.outDegrees.remove(edge)){
            toVertex.inDegrees.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outDegrees.add(edge);
        toVertex.inDegrees.add(edge);
        edges.add(edge);

    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));

    }

    @Override
    public void removeVertex(V v) {
        // 删除该点
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;

//        // 删除跟该点相关的边
//        vertex.outDegrees.forEach((Edge<V, E> edge) ->{
//            removeEdge(edge.from.value, edge.to.value);
//
//        // 将该条边从边集中删掉
//        edges.remove(edge);
//        });
//
//        vertex.inDegrees.forEach((Edge<V, E> edge) ->{
//        removeEdge(edge.to.value, edge.from.value);
//
//        // 将该条边从边集中删掉
//        edges.remove(edge);
//        });

        //如果你有边遍历，边删除的需求，请使用迭代器

        for (Iterator<Edge<V, E>> iterator = vertex.outDegrees.iterator();iterator.hasNext();){
            Edge<V, E> edge = iterator.next();
            //需要通过这条边找到这条边的终点，在终点edge.to的indegrees中把edge删掉
            edge.to.inDegrees.remove(edge);

            iterator.remove();
            edges.remove(edge);

        }

        for (Iterator<Edge<V, E>> iterator = vertex.inDegrees.iterator();iterator.hasNext();){
            Edge<V, E> edge = iterator.next();
            //需要通过这条边找到这条边的终点，在终点edge.to的indegrees中把edge删掉
            edge.from.outDegrees.remove(edge);

            iterator.remove();
            edges.remove(edge);

        }




    }



    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;

        //代码能来到这里，说明，起点和终点都存在，但不代表起点终点之间有边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);

        if ( fromVertex.outDegrees.remove(edge)){
            toVertex.inDegrees.remove(edge);
            edges.remove(edge);
        }

    }

    public void printGraph(){
        System.out.println("[Vertex] -------------------------");
        vertices.forEach((V v, Vertex<V, E> vertex)->{
            System.out.println(v);
            System.out.println("Out-----------------------");
            System.out.println(vertex.outDegrees);
            System.out.println("In-----------------------");
            System.out.println(vertex.inDegrees);

        });
        System.out.println("[edge] --------------------------");
        edges.forEach((Edge<V, E> edge) ->{
            System.out.println(edge);
        });
    }


}
