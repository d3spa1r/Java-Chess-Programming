package $Chess.globals.envi.pieces;

import $Chess.globals.envi.auxiliaries.Auxiliaries;
import $Chess.globals.envi.boards.*;
import $Chess.globals.envi.team.*;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class Queen extends Piece
{
	private static final int[] POSSIBLE_QUEEN_MOVE_POSITION_SEQUENCE = {-9, -8, -7, -1, 1, 7, 8, 9}; 
	
	private Queen (final int pieceCoordinate, 
				   final Team teamOfPiece)
	{
		super(pieceCoordinate, teamOfPiece);
	}

	@Override
	public Collection<Move> allPossibleLegalMoves (ChessBoard chessBoard) 
	{
		final ArrayList<Move> viableQueenMoves = new ArrayList<>();
		
		for (final int queenCoordinateDifferential : POSSIBLE_QUEEN_MOVE_POSITION_SEQUENCE)
		{
			int possibleQueenMoveDestination = this.pieceCoordinate;
			
			while (Auxiliaries.theTilePositionIsValid(possibleQueenMoveDestination))
			{
				if (queenPieceAtFirstColumn(this.pieceCoordinate, queenCoordinateDifferential) || 
					queenPieceAtEighthColumn(this.pieceCoordinate, queenCoordinateDifferential))
				{
					break;
				}
				
				if (Auxiliaries.theTilePositionIsValid(possibleQueenMoveDestination))
				{
					final Tile possibleTilePositionAfterMove = chessBoard.getTile(possibleQueenMoveDestination);
					
					if (!possibleTilePositionAfterMove.theTileIsOccupied())
					{
						viableQueenMoves.add(new Move.NonAttackingMove(chessBoard, 
																  	   this, 
																  	   possibleQueenMoveDestination));
					}
					else 
					{
						final Piece pieceAtTargetDestination = possibleTilePositionAfterMove.getPiece();
						final Team teamOfPiece = pieceAtTargetDestination.getTeamOfPiece();
						
						if (this.teamOfPiece != teamOfPiece)
						{
							viableQueenMoves.add(new Move.AttackingMove(chessBoard, 
																   	    this, 
																   	    possibleQueenMoveDestination, 
																   	    pieceAtTargetDestination));
						}
						
						break;
					}
				}
			}
		}
		
		return ImmutableList.copyOf(viableQueenMoves);
	}
	
	private static boolean queenPieceAtFirstColumn (final int currentCoordinate,
													final int possiblePositionDist)
	{
		return Auxiliaries.FIRST_COLUMN[currentCoordinate] && (possiblePositionDist == -9 ||
															   possiblePositionDist == -1 || 
															   possiblePositionDist == 7);
	}
	
	private static boolean queenPieceAtEighthColumn (final int currentCoordinate,
													final int possiblePositionDist)
	{
		return Auxiliaries.FIRST_COLUMN[currentCoordinate] && (possiblePositionDist == -7 ||
					   										   possiblePositionDist == 1 || 
					   										   possiblePositionDist == 9);
	}
}
