# stringmatching
A collection of string matching related algorithms implemented in Clojure.

## Usage
The algorithms should be usable for offline string matching, hence one should
be able to use the created data structures similar to indeces for lookups.

Typically this involves a step that creates the 'index' data structure used
for lookups, and one where other strings are queried against this index.

### Implemented algorithms
- Burghard-Keller tree (http://en.wikipedia.org/wiki/BK-tree)

## License

Copyright © 2013 Alecs Geuder

Distributed under the Eclipse Public License either version 1.0.
