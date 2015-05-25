package com.dhf.venus.server.command.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dhf.venus.server.command.Command;

/**
 * @author kim 2015年5月25日
 */
@Sharable
public class NettyCommandHandler extends ChannelInboundHandlerAdapter {

	private final static Log LOGGER = LogFactory.getLog(NettyCommandHandler.class);

	private final Command command;

	private final String exit;

	public NettyCommandHandler(Command command, String exit) {
		super();
		this.exit = exit;
		this.command = command;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		String command = ByteBuf.class.cast(msg).toString(Charset.defaultCharset());
		if (this.exit.equalsIgnoreCase(command.trim())) {
			ctx.close();
		} else {
			this.command.command(command);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		NettyCommandHandler.LOGGER.error(cause.getMessage(), cause);
		ctx.close();
	}
}