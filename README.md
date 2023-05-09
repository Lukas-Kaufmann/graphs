# README

Implementation of the graph datastructure in Java for an algorithm class.


## Representations Pro/Cons

|                  | Advantages                                                            | Disadvantages                                                                 |
|------------------|-----------------------------------------------------------------------|-------------------------------------------------------------------------------|
| Adjecency matrix | quick random access on edges                                          | inefficient if graph has few edges. Then it would store a lot of "no edge"    |
| Adjecency list   | space efficient if graph has few edges. Stores only the needed edges. | slower random access to edges. Cannot be directly looked up in a edge matrix. |


## Reading from file

For that part the json format was used, a graph on a file could look like this:

```
{
  "vertexes": [
    "A", "B"
  ],
  "edges": [
    {
      "from": "A",
      "to": "B",
      "attributes": {
        "weight" : 1
      }
    }
  ]
}
```

A sample file to load has been provided [here](sample-graph-input.json)

This structure is then loaded by creating a new empty graph then adding all the vertexes and edges.

## Pretty print of graph

This part has not been implemented.

## Traversals

Traversing was implemented by having a Traverser interfaces, which is implemented by the different strategies.
Depth-, Breadth- and RecursiveDepthTraversal.

The traverse() method can then be called on any graph.

## Determine if euler path exists

Determining whether or not a graph contains a euler path is done by checking the order of each vertex.

If a graph contains more than two verteces with odd order it doesn't contain an euler path.