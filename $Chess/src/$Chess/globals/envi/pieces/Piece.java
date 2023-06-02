package $Chess.globals.envi.pieces;

import java.util.Collection;

import $Chess.globals.envi.boards.ChessBoard;
import $Chess.globals.envi.boards.Move;
import $Chess.globals.envi.team.*;

public abstract class Piece 
{
	protected final int pieceCoordinate;
	protected final Team teamOfPiece;
	protected final boolean hasUsedFirstMove;
	
	protected Piece (final int pieceCoordinate, 
					 final Team teamOfPiece)
	{
		this.teamOfPiece = teamOfPiece;
		this.pieceCoordinate = pieceCoordinate;
		this.hasUsedFirstMove = false;
	}
	
	public Team getTeamOfPiece()
	{
		return this.teamOfPiece;
	}
	
	public boolean hasUsedFirstMove()
	{
		return this.hasUsedFirstMove;
	}
	
	public abstract Collection<Move> allPossibleLegalMoves (final ChessBoard chessBoard);
}
