# README

Implementation of the graph datastructure in Java.


## Representations Pro/Cons

|                  | Advantages                                                            | Disadvantages                                                                 |
|------------------|-----------------------------------------------------------------------|-------------------------------------------------------------------------------|
| Adjecency matrix | quick random access on edges                                          | inefficient if graph has few edges. Then it would store a lot of "no edge"    |
|                  |                                                                       |                                                                               |
| Adjecency list   | space efficient if graph has few edges. Stores only the needed edges. | slower random access to edges. Cannot be directly looked up in a edge matrix. |

