/**
 * 
 */
package org.tonoplace.mcmods.timetracker.network;

import java.util.concurrent.atomic.AtomicLong;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * Response message to the client when the client sends a 
 * @see RequestAvailableStatistics message.
 * @author BlackTachyon
 *
 */
public class ReplyAvailableStatistics implements IMessage {
   
   /**
    * 
    */
   private long sequence;
   
	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.simpleimpl.IMessage#fromBytes(io.netty.buffer.ByteBuf)
	 */
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.simpleimpl.IMessage#toBytes(io.netty.buffer.ByteBuf)
	 */
	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub

	}

	private static final AtomicLong SEQUENCE_GENERATOR = new AtomicLong();
}
