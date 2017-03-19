package ua.dudka.dijkstra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.dudka.dijkstra.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor()
public class Graph {
    private final List<Vertex> nodes;
    private final List<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

    }

    public void addNode(String name) {
        nodes.add(new Vertex(name));
    }

    public void removeNode(String name) {
        nodes.removeIf(v -> v.getName().equals(name));
        edges.removeIf(e -> e.getStart().getName().equals(name) || e.getEnd().getName().equals(name));
    }

    public void updateNodeCoordinates(String name, Double x, Double y) {
        Vertex vertex = findVertexByName(name);
        vertex.updateCoordinates(x, y);
    }

    public void addEdge(String start, String end, int weight) {
        Vertex startVertex = findVertexByName(start);
        Vertex endVertex = findVertexByName(end);

        edges.add(new Edge(startVertex, endVertex, weight));
    }

    public Vertex findVertexByName(String name) {
        return nodes.stream()
                .filter(n -> n.getName().equals(name))
                .findAny().orElseThrow(NotFoundException::new);
    }

    public void removeEdge(Integer id) {
        edges.removeIf(e -> e.getId() == id);
    }

    public void clear() {
        this.nodes.clear();
        this.edges.clear();
    }
}