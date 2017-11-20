package view;

import controller.Controller;

public class View {
		
		private View(){
		}
		
		private static View INSTANCE;
		
		public static View getInstance()
		{
			if(INSTANCE == null)
				INSTANCE = new View();
			return INSTANCE;
		}
}
