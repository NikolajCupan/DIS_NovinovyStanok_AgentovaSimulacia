package org.example.agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
import org.example.simulation.*;
import org.example.managers.*;
import org.example.continualAssistants.*;
import org.example.instantAssistants.*;

//meta! id="4"
public class AgentOkolie extends Agent
{
	private Stat statCakanieSystem;

	private void customPrepareReplication()
	{
		this.statCakanieSystem = new Stat();
	}

	private void customInit()
	{
		this.addOwnMessage(Mc.holdPrichodZakaznika);
	}

	public double getPriemerneCakanieSystem()
	{
		return this.statCakanieSystem.mean();
	}

	public void pridajCakanieSystem(MessageForm odchodZakaznikaSprava)
	{
		this.statCakanieSystem.addSample(((MyMessage)odchodZakaznikaSprava).getCelkoveCakanieSystem());
	}

	public AgentOkolie(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();

		this.customInit();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		this.customPrepareReplication();
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerOkolie(Id.managerOkolie, mySim(), this);
		new SchedulerPrichodZakaznika(Id.schedulerPrichodZakaznika, mySim(), this);
		addOwnMessage(Mc.noticeOdchodZakaznika);
		addOwnMessage(Mc.noticeVnutornaPrichodZakaznika);
		addOwnMessage(Mc.noticeInicializaciaSimulacie);
	}
	//meta! tag="end"
}