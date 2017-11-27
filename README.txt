ajouter au projet la bibliothèque de graphes : 
    jgrapht-core-1.0.1.jar
consulter l'API de jgrapht sur le web : 
    (DirectedGraph, DefaultDirectedGraph, DefaultEdge, ...)
    
pour le graphe (dans le model)
-créer la classe Vertex ( 2 attributs x,y ... )
-étendre DefaultEdge (jgrapht)
-Graph <Vertex, Edge>
faire le graphe de la grille sans mur
affichage du graphe avec graphviz (creer un fichier .dot)

http://www.objectaid.com
plugin Eclipse pour construire automatiquement le diagramme UML de nos classes.

Dans Graph :

public void graphToDot()
	throws IOException{
	try { FileWriter = fstream = new Filewriter("./graph.dot, false);
	BufferedWriter out = new BufferedWriter(fstream);
	out.write ("Graph D{\n");
	for (Vertex v: this.vertexSet()){
	out.write(v.dotString());
	}
	for (Edge e: this.edgeSet()){
	out.write(e.dotString());
	}
	out.write("}\n");
	out.close();
}