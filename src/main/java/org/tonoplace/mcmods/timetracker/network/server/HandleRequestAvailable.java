/**
 * 
 */
package org.tonoplace.mcmods.timetracker.network.server;

import org.tonoplace.mcmods.timetracker.network.ReplyAvailableStatistics;
import org.tonoplace.mcmods.timetracker.network.RequestAvailableStatistics;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author BlackTachyon
 *
 */
public class HandleRequestAvailable implements IMessageHandler<RequestAvailableStatistics, ReplyAvailableStatistics> {

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.simpleimpl.IMessageHandler#onMessage(cpw.mods.fml.common.network.simpleimpl.IMessage, cpw.mods.fml.common.network.simpleimpl.MessageContext)
	 */
	@Override
	public ReplyAvailableStatistics onMessage(RequestAvailableStatistics message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
