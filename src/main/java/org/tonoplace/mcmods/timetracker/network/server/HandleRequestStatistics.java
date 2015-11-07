/**
 * 
 */
package org.tonoplace.mcmods.timetracker.network.server;

import org.tonoplace.mcmods.timetracker.network.ReplyCurrentStatistics;
import org.tonoplace.mcmods.timetracker.network.RequestStatistics;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author BlackTachyon
 *
 */
public class HandleRequestStatistics implements IMessageHandler<RequestStatistics, ReplyCurrentStatistics> {

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.simpleimpl.IMessageHandler#onMessage(cpw.mods.fml.common.network.simpleimpl.IMessage, cpw.mods.fml.common.network.simpleimpl.MessageContext)
	 */
	@Override
	public ReplyCurrentStatistics onMessage(RequestStatistics message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
