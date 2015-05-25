package com.dhf.venus.server.command.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author kim 2015年5月25日
 */
public class NettyServer {

	private final NettyCommandHandler handler;

	private final EventLoopGroup bossGroup = new NioEventLoopGroup();

	private final EventLoopGroup workerGroup = new NioEventLoopGroup();

	private final int port;

	public NettyServer(NettyCommandHandler handler, int port) {
		super();
		this.port = port;
		this.handler = handler;
	}

	public void init() throws Exception {
		ServerBootstrap boot = new ServerBootstrap();
		boot.group(this.bossGroup, this.workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(NettyServer.this.handler);
			}
		});
		boot.bind(this.port);
	}

	public void destory() throws Exception {
		this.bossGroup.shutdownGracefully();
		this.workerGroup.shutdownGracefully();
	}
}