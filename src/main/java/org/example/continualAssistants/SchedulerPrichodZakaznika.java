package org.example.continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import org.example.simulation.*;
import org.example.agents.*;
import org.example.vlastne.GeneratorNasad;
import org.example.vlastne.Prezenter;

//meta! id="11"
public class SchedulerPrichodZakaznika extends Scheduler
{
	private GeneratorNasad rngGeneratorNasad;
	private ExponentialRNG rngPrichodZakaznika;

	public void customInit()
	{
		this.rngGeneratorNasad = new GeneratorNasad();
		this.rngPrichodZakaznika = new ExponentialRNG(100.0, this.rngGeneratorNasad.generator());
	}

	public SchedulerPrichodZakaznika(int id, Simulation mySim, CommonAgent myAgent)
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

	//meta! sender="AgentOkolie", id="12", type="Start"
	public void processStart(MessageForm message)
	{
		double trvaniePrichodu = this.rngPrichodZakaznika.sample();
		if (this.mySim().currentTime() + trvaniePrichodu > ((MySimulation)this.mySim()).getTrvanieSimulacie())
		{
			// Vyprsanie simulacneho casu, neplanuj dalsie prichody
		}
		else
		{
			message.setCode(Mc.holdPrichodZakaznika);
			hold(trvaniePrichodu, message);
		}
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.holdPrichodZakaznika:
			// Dalsi prichod
			MyMessage dalsiPrichodSprava = (MyMessage)message.createCopy();

			double trvaniePrichodu = this.rngPrichodZakaznika.sample();
			if (this.mySim().currentTime() + trvaniePrichodu > ((MySimulation)this.mySim()).getTrvanieSimulacie())
			{
				// Vyprsanie simulacneho casu, neplanuj dalsie prichody
			}
			else
			{
				hold(trvaniePrichodu, dalsiPrichodSprava);

				// Oznamenie prichodu svojmu manazerovi
				message.setCode(Mc.noticeVnutornaPrichodZakaznika);
				message.setAddressee(this.myAgent().manager());
				notice(message);
			}
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
