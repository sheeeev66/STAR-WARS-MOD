package net.star.wars.materials.tools;

// latest edit: 13/8/2020, by: OverlordsIII

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.star.wars.materials.StarWarsMaterials;

import java.util.stream.Stream;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.server.PlayerStream;
public class LightsaberItem extends Item {

    public LightsaberItem(Settings settings) {
        super(settings);
    }

    boolean lightsaberOn = false;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        // save nbt data
        ItemStack stack = playerEntity.getMainHandStack();
        CompoundTag tag = stack.getOrCreateTag();
        lightsaberOn = tag.getBoolean("on");
        
        //Send a packet to the client to play our sound
        BlockPos pos = playerEntity.getBlockPos();
        MinecraftClient.getInstance().
        Stream<PlayerEntity> watchingPlayers = PlayerStream.watching(world, pos);
        PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
        watchingPlayers.forEach(player ->
        ServerSidePacketRegistry.INSTANCE.sendToPlayer(player,StarWarsMaterials.LIGHTSABER_HUM_PACKET_ID,passedData));
        playerEntity.playSound(
            lightsaberOn ? StarWarsMaterials.LIGHTSABER_ON : StarWarsMaterials.LIGHTSABER_OFF,
            1.0f, 1.0f
        );
        
        lightsaberOn =  !lightsaberOn;
        
        tag.putBoolean("on", lightsaberOn);
        
        return super.use(world, playerEntity, hand);
    
   }
}