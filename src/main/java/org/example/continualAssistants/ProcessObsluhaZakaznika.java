package org.example.continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import OSPRNG.TriangularRNG;
import org.example.simulation.*;
import org.example.agents.*;
import OSPABA.Process;
import org.example.vlastne.GeneratorNasad;

//meta! id="14"
public class ProcessObsluhaZakaznika extends Process
{
	private GeneratorNasad rngGeneratorNasad;
	private TriangularRNG rngObsluhaZakaznika;

	public void customInit()
	{
		this.rngGeneratorNasad = new GeneratorNasad();
		this.rngObsluhaZakaznika = new TriangularRNG(60.0, 100.0, 180.0, this.rngGeneratorNasad.generator());
	}

	public ProcessObsluhaZakaznika(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);

		this.customInit();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentStanok", id="15", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.holdObsluhaZakaznika);
		hold(this.rngObsluhaZakaznika.sample(), message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.holdObsluhaZakaznika:
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
	public AgentStanok myAgent()
	{
		return (AgentStanok)super.myAgent();
	}

}
