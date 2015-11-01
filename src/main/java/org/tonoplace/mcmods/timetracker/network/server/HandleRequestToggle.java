/**
 * 
 */
package org.tonoplace.mcmods.timetracker.network.server;

import org.tonoplace.mcmods.timetracker.network.ReplyOther;
import org.tonoplace.mcmods.timetracker.network.RequestToggleCurrentStatistic;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author BlackTachyon
 *
 */
public class HandleRequestToggle implements IMessageHandler<RequestToggleCurrentStatistic, ReplyOther> {

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.simpleimpl.IMessageHandler#onMessage(cpw.mods.fml.common.network.simpleimpl.IMessage, cpw.mods.fml.common.network.simpleimpl.MessageContext)
	 */
	@Override
	public ReplyOther onMessage(RequestToggleCurrentStatistic message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
