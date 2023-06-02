package $Chess.globals.envi.pieces;

import $Chess.globals.envi.auxiliaries.Auxiliaries;
import $Chess.globals.envi.boards.*;
import $Chess.globals.envi.team.*;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class Knight extends Piece
{
	@SuppressWarnings("unused")
	private static final int[] POTENTIAL_KNIGHT_MOVE_POSITIONS = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	private Knight (final int pieceCoordinate, 
					final Team teamOfPiece)
	{
		super(pieceCoordinate, teamOfPiece);
	}
	
	@Override
	public Collection<Move> allPossibleLegalMoves (final ChessBoard chessBoard)
	{
		final ArrayList<Move> viableKnightMoves = new ArrayList<>();
		
		for (final int knightCoordinateDifferential : POTENTIAL_KNIGHT_MOVE_POSITIONS)
		{
			int possibleKnightMoveDestination = this.pieceCoordinate + knightCoordinateDifferential;
			
			if (Auxiliaries.theTilePositionIsValid (possibleKnightMoveDestination))
			{
				if (knightPieceAtFirstColumn(this.pieceCoordinate, knightCoordinateDifferential) || 
					knightPieceAtSecondColumn(this.pieceCoordinate, knightCoordinateDifferential) ||
					knightPieceAtSeventhColumn(this.pieceCoordinate, knightCoordinateDifferential) || 
					knightPieceAtEighthColumn(this.pieceCoordinate, knightCoordinateDifferential))
				{
					break;
				}
				
				final Tile possibleTilePositionAfterMove = chessBoard.getTile(possibleKnightMoveDestination);
				
				if (!possibleTilePositionAfterMove.theTileIsOccupied())
				{
					viableKnightMoves.add(new Move.NonAttackingMove(chessBoard, 
															  		this, 
															  		possibleKnightMoveDestination));
				}
				else 
				{
					final Piece pieceAtTargetDestination = possibleTilePositionAfterMove.getPiece();
					final Team teamOfPiece = pieceAtTargetDestination.getTeamOfPiece();
					
					if (this.teamOfPiece != teamOfPiece)
					{
						viableKnightMoves.add(new Move.AttackingMove(chessBoard, 
															   		 this, 
															   		 possibleKnightMoveDestination, 
															   		 pieceAtTargetDestination));
					}
				}
			}
		}
		return ImmutableList.copyOf(viableKnightMoves);
	}


	@SuppressWarnings("unused")
	private static boolean knightPieceAtFirstColumn (final int currentCoordinate, 
											   	     final int possiblePositionDist)
	{
		return Auxiliaries.FIRST_COLUMN[currentCoordinate] && ((possiblePositionDist == -17) || 
															   (possiblePositionDist == -10) || 
															   (possiblePositionDist == 6) || 
															   (possiblePositionDist == 15));
	}
	
	@SuppressWarnings("unused")
	private static boolean knightPieceAtSecondColumn (final int currentCoordinate, 
											    	  final int possiblePositionDist)
	{
		return Auxiliaries.SECOND_COLUMN[currentCoordinate] && ((possiblePositionDist == -10) || 
																(possiblePositionDist == 6));
	}
	
	@SuppressWarnings("unused")
	private static boolean knightPieceAtSeventhColumn (final int currentCoordinate, 
												 	   final int possiblePositionDist)
	{
		return Auxiliaries.SEVENTH_COLUMN[currentCoordinate] && ((possiblePositionDist == -6) || 
																 (possiblePositionDist == 10));
	}
	
	@SuppressWarnings("unused")
	private static boolean knightPieceAtEighthColumn (final int currentCoordinate, 
											    	  final int possiblePositionDist)
	{
		return Auxiliaries.EIGHTH_COLUMN[currentCoordinate] && ((possiblePositionDist == 17) || 
																(possiblePositionDist == 10) || 
																(possiblePositionDist == -6) || 
																(possiblePositionDist == -15));
	}
}

