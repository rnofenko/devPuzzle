using NUnit.Framework;
using System.Linq;
using System.Collections.Generic;

namespace DevPuzzle.Tests.Graph.Wip
{
    [TestFixture]
    public class KruskalReallySpecialSubtreeTests
    {
        [Test]
        public void Sample1()
        {
            var result = Kruskals(4, new List<int> { 1,1,4,2,3,3 }, new List<int> { 2, 3, 1, 4, 2, 4 }, new List<int> { 5, 3, 6, 7, 4, 5 });
            Assert.AreEqual(12, result);
        }

        [Test]
        public void Sample2()
        {
            var result = Kruskals(4, new List<int> { 1, 3, 4, 1, 3 }, new List<int> { 2, 2, 3, 4, 1 }, new List<int> { 1, 150, 99, 100, 200 });
            Assert.AreEqual(200, result);
        }

        public int Kruskals(int gNodes, List<int> gFrom, List<int> gTo, List<int> gWeight)
        {
            var edges = new List<Edge>();
            for(var i = 0; i < gFrom.Count; i++)
            {
                edges.Add(new Edge(gFrom[i], gTo[i], gWeight[i]));
            }

            var vertexToTree = new Dictionary<int, Tree>();

            foreach(var edge in edges.OrderBy(x => x.Weight))
            {
                vertexToTree.TryGetValue(edge.Vertex1, out var tree1);
                vertexToTree.TryGetValue(edge.Vertex2, out var tree2);

                if (tree1 == null)
                {
                    if (tree2 == null)
                    {
                        var tree = new Tree();
                        tree.Add(edge, vertexToTree);
                    }
                    else
                    {
                        tree2.Add(edge, vertexToTree);
                    }
                }
                else
                {
                    if (tree2 == null)
                    {
                        tree1.Add(edge, vertexToTree);
                    }
                    else
                    {
                        if(tree1 != tree2)
                        {
                            foreach(var treeEdge in tree2.Edges)
                            {
                                tree1.Add(treeEdge, vertexToTree);
                            }
                            tree1.Add(edge, vertexToTree);
                        }
                    }
                }
            }

            return vertexToTree.Values.Distinct().SelectMany(t => t.Edges).Sum(e => e.Weight);
        }

        class Tree
        {
            public List<Edge> Edges = new List<Edge>();

            public void Add(Edge edge, Dictionary<int, Tree> map)
            {
                Edges.Add(edge);
                if (map.ContainsKey(edge.Vertex1))
                {
                    map[edge.Vertex1] = this;
                }
                else
                {
                    map.Add(edge.Vertex1, this);
                }
                if (map.ContainsKey(edge.Vertex2))
                {
                    map[edge.Vertex2] = this;
                }
                else
                {
                    map.Add(edge.Vertex2, this);
                }
            }
        }

        class Edge
        {
            public int Vertex1;
            public int Vertex2;
            public int Weight;

            public Edge(int v1, int v2, int weight)
            {
                Vertex1 = v1;
                Vertex2 = v2;
                Weight = weight;
            }

            public override string ToString()
            {
                return Weight.ToString();
            }
        }
    }
}
