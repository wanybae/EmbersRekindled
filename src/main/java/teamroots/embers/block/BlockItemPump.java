package teamroots.embers.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamroots.embers.tileentity.TileEntityItemPipe;
import teamroots.embers.tileentity.TileEntityItemPump;

public class BlockItemPump extends BlockTEBase {
	public BlockItemPump(Material material, String name, boolean addToTab) {
		super(material, name, addToTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityItemPump();
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block){
		if (world.getTileEntity(pos) != null){
			((TileEntityItemPump)world.getTileEntity(pos)).updateNeighbors(world);
			world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 3);
		}
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor){
		if (world.getTileEntity(pos) != null){
			((TileEntityItemPump)world.getTileEntity(pos)).updateNeighbors(world);
		}
	}
	
	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side){
		return true;
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		if (world.getTileEntity(pos) != null){
			((TileEntityItemPump)world.getTileEntity(pos)).updateNeighbors(world);
		}
	}
	
	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side){
		return true;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		double x1 = 0.3125;
		double y1 = 0.3125;
		double z1 = 0.3125;
		double x2 = 0.6875;
		double y2 = 0.6875;
		double z2 = 0.6875;
		
		if (source.getTileEntity(pos) instanceof TileEntityItemPump){
			TileEntityItemPump pipe = ((TileEntityItemPump)source.getTileEntity(pos));
			if (pipe.up != TileEntityItemPump.EnumPipeConnection.NONE){
				y2 = 1;
			}
			if (pipe.down != TileEntityItemPump.EnumPipeConnection.NONE){
				y1 = 0;
			}
			if (pipe.north != TileEntityItemPump.EnumPipeConnection.NONE){
				z1 = 0;
			}
			if (pipe.south != TileEntityItemPump.EnumPipeConnection.NONE){
				z2 = 1;
			}
			if (pipe.west != TileEntityItemPump.EnumPipeConnection.NONE){
				x1 = 0;
			}
			if (pipe.east != TileEntityItemPump.EnumPipeConnection.NONE){
				x2 = 1;
			}
		}
		
		return new AxisAlignedBB(x1,y1,z1,x2,y2,z2);
	}
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player){
		super.onBlockHarvested(world, pos, state, player);
		if (world.getTileEntity(pos.up()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.up())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.down()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.down())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.north()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.north())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.south()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.south())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.west()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.west())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.east()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.east())).updateNeighbors(world);
		}
	}
}
