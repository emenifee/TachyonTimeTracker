/**
 * 
 */
package org.tonoplace.mcmods.timetracker.network.client;

import org.tonoplace.mcmods.timetracker.network.ReplyAvailableStatistics;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author elijah
 *
 */
public class HandleReplyAvailable implements IMessageHandler<ReplyAvailableStatistics, IMessage> {

	@Override
	public IMessage onMessage(ReplyAvailableStatistics message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
