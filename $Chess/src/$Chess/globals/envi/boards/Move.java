package $Chess.globals.envi.boards;

import $Chess.globals.envi.pieces.Piece;

public abstract class Move 
{
	final ChessBoard chessBoard;
	final Piece movedPiece;
	final int pieceDestination;
	
	private Move (final ChessBoard chessBoard, 
				  final Piece movedPiece, 
				  final int pieceDestination)
	{
		this.chessBoard = chessBoard;
		this.movedPiece = movedPiece;
		this.pieceDestination = pieceDestination;
	}
	
	public static final class NonAttackingMove extends Move
	{
		public NonAttackingMove (final ChessBoard chessBoard, 
								 final Piece movedPiece, 
								 final int pieceDestination)
		{
			super(chessBoard, movedPiece, pieceDestination);
		}
	}
	
	public static final class AttackingMove extends Move
	{
		final Piece pieceAttacked;
		
		public AttackingMove (final ChessBoard chessBoard, 
							  final Piece movedPiece, 
						      final int pieceDestination,
					          final Piece pieceAttacked)
		{
			super(chessBoard, movedPiece, pieceDestination);
			this.pieceAttacked = pieceAttacked;
		}
	}
}
