import java.io.*;
import java.util.*;

/*

You work in an automated doll factory. Once dolls are assembled, they are sent to the shipping center via a series of autonomous delivery carts, each of which moves packages on a one-way route.

Given input that provides the (directed) steps that each cart takes as pairs, write a function that identifies all the start locations, and a collection of all of the possible ending locations for each start location.

In this diagram, starting locations are at the top and destinations are at the bottom - i.e. the graph is directed exclusively downward.

   A         E      J       Key:  [Origins]
  / \       / \      \             \
 B   C     F   L      M            [Destinations]
  \ /     /
   K     G
        / \
       H   I

paths = [
  ["A", "B"],
  ["A", "C"],
  ["B", "K"],
  ["C", "K"],
  ["E", "L"],
  ["F", "G"],
  ["J", "M"],
  ["E", "F"],
  ["G", "H"],
  ["G", "I"]
]

Expected output:
[ "A": ["K"],
  "E:" ["H", "L", "I"],
  "J": ["M"] ]

B  A
C  A
K  B, A, C
L  E
G  F
M  J
F  E
H  G, F, E
I  G, F, E

A B C E F J G : root

B C K L G M F H I : leaf

A = K

E =

 */

class RootAndLeaves {
  public static void main(String[] args) {
    String[][] paths = new String[][] {
      {"A", "B"},
      {"A", "C"},
      {"B", "K"},
      {"C", "K"},
      {"E", "L"},
      {"F", "G"},
      {"J", "M"},
      {"E", "F"},
      {"G", "H"},
      {"G", "I"},
    };
  }

}



