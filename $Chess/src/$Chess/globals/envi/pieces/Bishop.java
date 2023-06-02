package $Chess.globals.envi.pieces;

import $Chess.globals.envi.auxiliaries.Auxiliaries;
import $Chess.globals.envi.boards.*;
import $Chess.globals.envi.team.*;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class Bishop extends Piece 
{
	private static final int[] POSSIBLE_BISHOP_MOVE_POSITION_SEQUENCE = {-9, -7, 7, 9};
	
	private Bishop (final int pieceCoordinate, 
				    final Team teamOfPiece)
	{
		super(pieceCoordinate, teamOfPiece);
	}
	
	@Override
	public Collection<Move> allPossibleLegalMoves (ChessBoard chessBoard) 
	{
		final ArrayList<Move> viableBishopMoves = new ArrayList<>();
		
		for (final int bishopCoordinateDifferential : POSSIBLE_BISHOP_MOVE_POSITION_SEQUENCE)
		{
			int possibleBishopMoveDestination = this.pieceCoordinate;
			
			while (Auxiliaries.theTilePositionIsValid(possibleBishopMoveDestination))
			{
				if (bishopPieceAtFirstColumn(this.pieceCoordinate, bishopCoordinateDifferential) ||
					bishopPieceAtEighthColumn(this.pieceCoordinate, bishopCoordinateDifferential))
				{
					break;
				}
				
				possibleBishopMoveDestination = possibleBishopMoveDestination + bishopCoordinateDifferential;
				
				if (Auxiliaries.theTilePositionIsValid(possibleBishopMoveDestination))
				{
					final Tile possibleTilePositionAfterMove = chessBoard.getTile(possibleBishopMoveDestination);
					
					if (!possibleTilePositionAfterMove.theTileIsOccupied())
					{
						viableBishopMoves.add(new Move.NonAttackingMove(chessBoard, 
																  		this, 
																  		possibleBishopMoveDestination));
					}
					else 
					{
						final Piece pieceAtTargetDestination = possibleTilePositionAfterMove.getPiece();
						final Team teamOfPiece = pieceAtTargetDestination.getTeamOfPiece();
						
						if (this.teamOfPiece != teamOfPiece)
						{
							viableBishopMoves.add(new Move.AttackingMove(chessBoard, 
																   		 this, 
																   		 possibleBishopMoveDestination, 
																   		 pieceAtTargetDestination));
						}
						
						break;
					}
				}
			}
		}
		
		return ImmutableList.copyOf(viableBishopMoves);
	}
	
	private static boolean bishopPieceAtFirstColumn (final int currentCoordinate, 
													 final int possiblePositionDist)
	{
		return Auxiliaries.FIRST_COLUMN[currentCoordinate] && (possiblePositionDist == -9 ||
															   possiblePositionDist == 7);
	}
	
	private static boolean bishopPieceAtEighthColumn (final int currentCoordinate, 
			 										 final int possiblePositionDist)
	{
		return Auxiliaries.EIGHTH_COLUMN[currentCoordinate] && (possiblePositionDist == -7 ||
														        possiblePositionDist == 9);
	}
}
