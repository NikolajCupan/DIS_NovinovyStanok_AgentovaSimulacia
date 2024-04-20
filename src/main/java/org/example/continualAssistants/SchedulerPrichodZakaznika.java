package org.example.continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import org.example.simulation.*;
import org.example.agents.*;
import org.example.vlastne.GeneratorNasad;

//meta! id="11"
public class SchedulerPrichodZakaznika extends Scheduler
{
	private final GeneratorNasad rngGeneratorNasad;
	private final ExponentialRNG rngPrichodZakaznika;

	public SchedulerPrichodZakaznika(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);

		this.rngGeneratorNasad = new GeneratorNasad();
		this.rngPrichodZakaznika = new ExponentialRNG(120.0, this.rngGeneratorNasad.generator());
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentOkolie", id="12", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.holdPrichodZakaznika);
		hold(this.rngPrichodZakaznika.sample(), message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.holdPrichodZakaznika:
			MyMessage dalsiPrichodSprava = (MyMessage)message.createCopy();
			hold(this.rngPrichodZakaznika.sample(), dalsiPrichodSprava);

			assistantFinished(message);

			break;
		default:
			throw new RuntimeException("Neznamy kod spravy!");
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.start:
			processStart(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOkolie myAgent()
	{
		return (AgentOkolie)super.myAgent();
	}

}
