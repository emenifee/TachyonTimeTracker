/**
 * 
 */
package org.tonoplace.mcmods.timetracker.network.client;

import org.tonoplace.mcmods.timetracker.network.ReplyCurrentStatistics;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author elijah
 *
 */
public class HandleReplyCurrent implements IMessageHandler<ReplyCurrentStatistics, IMessage> {

	@Override
	public IMessage onMessage(ReplyCurrentStatistics message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
