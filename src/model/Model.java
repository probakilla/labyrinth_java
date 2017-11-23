package model;

import java.util.Random;
import java.util.Vector;

import controller.Controller;

public class Model {

	public static enum Directions {
		EAST, WEST, NORTH, SOUTH;
	};

	private Random random;
	private Graph graph;

	private Model() {}

	private static Model INSTANCE;

	public static Model getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Model();
		}
		return INSTANCE;
	}

	public void buildRandomPath( Vertex vertex ){
	    //une liste aleatoire des 4 directions
	    Vector<Directions> v = new Vector<Directions>();
	    for (int i=0;i<4;++i)
	        v.add(Directions.values()[i]);
	    
	    Directions directions[]=new Directions[4];
	    
	    for(int i=0;i<directions.length;++i){
	        int index=random.nextInt(v.size());
	        directions[i]=v.get(index);
	        v.remove(index);
	    }
	    
	    // pour chacune de ces directions, on avance en profondeur d’abord
	    for (int i=0;i<4;++i){
	        Directions dir=directions[i];
	        if(vertex.inBorders(dir, graph.getGRIDWIDTH(), graph.getGRIDHEIGHT()) && graph.doesntExist(vertex, dir)){
	            int x=vertex.getX();
	            int y =vertex.getY();
	            int xt=0, yt=0;
	            switch(dir){
	                case NORTH: xt = x ; yt = y-1; break ;
	                case SOUTH: xt = x ; yt = y+1; break ;
	                case EAST: xt = x+1; yt = y ; break ;
	                case WEST: xt = x-1; yt = y ; break ;
	            }
	            Vertex next = new Vertex( xt, yt, vertex.getNbr() + 1);
	            graph.addVertex( next  );
	            graph.addEdge( vertex , next );
	            buildRandomPath( next );
	        }
	    }
	}
}
