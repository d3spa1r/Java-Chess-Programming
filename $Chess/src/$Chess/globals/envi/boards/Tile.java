package $Chess.globals.envi.boards;

import java.util.Map;
import java.util.HashMap;

import $Chess.globals.envi.pieces.*;
import $Chess.globals.envi.auxiliaries.*;
import com.google.common.collect.ImmutableMap;

public abstract class Tile 
{
	protected final int position;
	
	public static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() 
	{
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		
		for (int i = 0; i < Auxiliaries.TOTAL_GRID_NUMBER; i = i + 1)
		{
			emptyTileMap.put(i, new EmptyTile(i));
		}
		
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
	public static Tile makeTile (final int position, 
								 final Piece piece)
	{
		return piece != null ? new OccupiedTile(position, piece) : EMPTY_TILES.get(position); 
	}
	
	private Tile (final int position)
	{
		this.position = position;
	}

	public abstract boolean theTileIsOccupied();
	
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile
	{
		private EmptyTile (final int position)
		{
			super(position);
		}
		
		@Override
		public boolean theTileIsOccupied()
		{
			return false;
		}
		
		@Override
		public Piece getPiece()
		{
			return null;
		}
	}
	
	public static final class OccupiedTile extends Tile
	{
		private final Piece pieceOccupyingTile;
		
		private OccupiedTile (final int position, 
							  final Piece pieceOccupyingTile)
		{
			super(position);
			this.pieceOccupyingTile = pieceOccupyingTile;
		}
		
		@Override
		public boolean theTileIsOccupied()
		{
			return true;
		}
		
		@Override
		public Piece getPiece()
		{
			return this.pieceOccupyingTile;
		}
	}
}
