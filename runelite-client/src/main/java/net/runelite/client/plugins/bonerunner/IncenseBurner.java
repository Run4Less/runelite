package net.runelite.client.plugins.bonerunner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
class IncenseBurner
{
	private final Instant start = Instant.now();
	private final int id;
	private double countdownTimer;
	private Boolean notification;
}
