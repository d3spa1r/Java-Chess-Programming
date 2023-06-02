package $Chess.globals.envi.team;

public enum Team 
{
	BLACK
	{
		public int getSide()
		{
			return 1;
		}

		@Override
		public boolean isWhite() 
		{
			return false;
		}

		@Override
		public boolean isBlack() 
		{
			return true;
		}
	},
	WHITE
	{
		public int getSide() 
		{
			return -1;
		}

		@Override
		public boolean isWhite() 
		{
			return true;
		}

		@Override
		public boolean isBlack() 
		{
			return false;
		}
	};
	
	public abstract int getSide();
	public abstract boolean isWhite();
	public abstract boolean isBlack();
}
