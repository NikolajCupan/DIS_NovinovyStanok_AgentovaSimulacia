package org.example.managers;

import OSPABA.*;
import org.example.simulation.*;
import org.example.agents.*;
import org.example.continualAssistants.*;
import org.example.instantAssistants.*;

//meta! id="4"
public class ManagerOkolie extends Manager
{
	public ManagerOkolie(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="SchedulerPrichodZakaznika", id="12", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentModel", id="21", type="Notice"
	public void processNoticeInicializaciaSimulacie(MessageForm message)
	{
		MyMessage spusteniePrichodovSprava = new MyMessage(this.mySim());
		spusteniePrichodovSprava.setAddressee(this.myAgent().findAssistant(Id.schedulerPrichodZakaznika));
		startContinualAssistant(spusteniePrichodovSprava);
	}

	//meta! sender="SchedulerPrichodZakaznika", id="23", type="Notice"
	public void processNoticeVnutornaPrichodZakaznika(MessageForm message)
	{
		// Oznam o prichode zakaznika
		message.setCode(Mc.noticePrichodZakaznika);
		message.setAddressee(Id.agentModel);
		notice(message);
	}

	//meta! sender="AgentModel", id="26", type="Notice"
	public void processNoticeOdchodZakaznika(MessageForm message)
	{
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.noticeVnutornaPrichodZakaznika:
			processNoticeVnutornaPrichodZakaznika(message);
		break;

		case Mc.noticeOdchodZakaznika:
			processNoticeOdchodZakaznika(message);
		break;

		case Mc.noticeInicializaciaSimulacie:
			processNoticeInicializaciaSimulacie(message);
		break;

		case Mc.finish:
			processFinish(message);
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
