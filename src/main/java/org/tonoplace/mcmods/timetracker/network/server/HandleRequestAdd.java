package org.tonoplace.mcmods.timetracker.network.server;

import org.tonoplace.mcmods.timetracker.network.ReplyOther;
import org.tonoplace.mcmods.timetracker.network.RequestAddStatistic;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class HandleRequestAdd implements IMessageHandler<RequestAddStatistic, ReplyOther> {

	@Override
	public ReplyOther onMessage(RequestAddStatistic message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
