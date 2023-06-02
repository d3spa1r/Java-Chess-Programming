package $Chess.globals.envi.pieces;

import $Chess.globals.envi.auxiliaries.Auxiliaries;
import $Chess.globals.envi.boards.*;
import $Chess.globals.envi.team.*;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class Pawn extends Piece
{
	private static final int[] POTENTIAL_PAWN_MOVE_POSITION = {7, 8, 9, 16};
	
	private Pawn (final int pieceCoordinate,
				  final Team teamOfPiece)
	{
		super(pieceCoordinate, teamOfPiece);
	}

	@Override
	public Collection<Move> allPossibleLegalMoves (ChessBoard chessBoard) 
	{
		
		final ArrayList<Move> viablePawnMoves = new ArrayList<>();
		
		for (final int pawnCoordinateDifferential : POTENTIAL_PAWN_MOVE_POSITION)
		{
			final int possiblePawnMoveDestination = this.pieceCoordinate + 
													(this.teamOfPiece.getSide() * 
													pawnCoordinateDifferential);
			
			if (!Auxiliaries.theTilePositionIsValid(possiblePawnMoveDestination))
			{
				continue;
			}
			
			if (pawnCoordinateDifferential == 8 && 
				!chessBoard.getTile(possiblePawnMoveDestination).theTileIsOccupied())
			{
				viablePawnMoves.add(new Move.NonAttackingMove(chessBoard, 
															  this, 
															  possiblePawnMoveDestination));
			}
			else if (pawnCoordinateDifferential == 16 && this.hasUsedFirstMove() && 
					(Auxiliaries.SECOND_ROW[this.pieceCoordinate] && this.getTeamOfPiece().isBlack()) ||
					(Auxiliaries.SEVENTH_ROW[this.pieceCoordinate] && this.getTeamOfPiece().isWhite()))
			{
				final int behindPossiblePawnMoveDestination = this.pieceCoordinate + (this.teamOfPiece.getSide() * 8);
				
				if (!chessBoard.getTile(behindPossiblePawnMoveDestination).theTileIsOccupied() && 
					!chessBoard.getTile(possiblePawnMoveDestination).theTileIsOccupied())
				{
					viablePawnMoves.add(new Move.NonAttackingMove(chessBoard, null, possiblePawnMoveDestination));
				}
			}
			else if (pawnCoordinateDifferential == 7)
			{
				
			}
			else if (pawnCoordinateDifferential == 9)
			{
				
			}
		}
		
		return ImmutableList.copyOf(viablePawnMoves);
	}

	public boolean hasUsedFirstMove() 
	{
		
		return false;
	}
}
