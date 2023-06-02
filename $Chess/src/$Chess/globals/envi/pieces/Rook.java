package $Chess.globals.envi.pieces;

import $Chess.globals.envi.auxiliaries.Auxiliaries;
import $Chess.globals.envi.boards.*;
import $Chess.globals.envi.team.*;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class Rook extends Piece
{
	private static final int[] POTENTIAL_ROOK_MOVE_POSITION_SEQUENCE = {-8, -1, 1, 8};
	
	private Rook (final int pieceCoordinate, final Team teamOfPiece)
	{
		super(pieceCoordinate, teamOfPiece);
	}

	@Override
	public Collection<Move> allPossibleLegalMoves (ChessBoard chessBoard) 
	{	
		final ArrayList<Move> viableRookMoves = new ArrayList<>();
		
		for (final int rookCoordinateDifferential : POTENTIAL_ROOK_MOVE_POSITION_SEQUENCE)
		{
			int possibleRookMoveDestination = this.pieceCoordinate;
			
			while (Auxiliaries.theTilePositionIsValid(possibleRookMoveDestination))
			{
				possibleRookMoveDestination = possibleRookMoveDestination + rookCoordinateDifferential;
				
				if (rookPieceAtFirstColumn(this.pieceCoordinate, rookCoordinateDifferential) || 
					rookPieceAtEighthColumn(this.pieceCoordinate, rookCoordinateDifferential))
				{
					break;
				}
				
				if (Auxiliaries.theTilePositionIsValid(possibleRookMoveDestination))
				{
					final Tile possibleTilePositionAfterMove = chessBoard.getTile(possibleRookMoveDestination);
					
					if (!possibleTilePositionAfterMove.theTileIsOccupied())
					{
						viableRookMoves.add(new Move.NonAttackingMove(chessBoard, 
																  	  this, 
																  	  possibleRookMoveDestination));
					}
					else 
					{
						final Piece pieceAtTargetDestination = possibleTilePositionAfterMove.getPiece();
						final Team teamOfPiece = pieceAtTargetDestination.getTeamOfPiece();
						
						if (this.teamOfPiece != teamOfPiece)
						{
							viableRookMoves.add(new Move.AttackingMove(chessBoard, 
																   	   this, 
																   	   possibleRookMoveDestination, 
																   	   pieceAtTargetDestination));
						}
						
						break;
					}
				}
			}
		}
		
		return ImmutableList.copyOf(viableRookMoves);
	}
	
	public static boolean rookPieceAtFirstColumn (final int currentCoordinate,
												  final int possiblePositionDist)
	{
		return Auxiliaries.FIRST_COLUMN[currentCoordinate] && possiblePositionDist == -1;
	}
	
	public static boolean rookPieceAtEighthColumn (final int currentCoordinate,
			  									   final int possiblePositionDist)
	{
		return Auxiliaries.EIGHTH_COLUMN[currentCoordinate] && possiblePositionDist == 1;
	}
}
