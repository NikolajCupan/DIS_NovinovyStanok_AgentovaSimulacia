package org.example.simulation;

import OSPABA.*;
import OSPStat.Stat;
import org.example.agents.*;

public class MySimulation extends Simulation
{
	private double trvanieSimulacie;

	// Statistiky
	private Stat statCakanieSystem;
	private Stat statCakanieFrontStanok;
	private Stat statRadFrontStanok;

	public void customInit(double trvanieSimulacie)
	{
		if (trvanieSimulacie <= 0)
		{
			throw new RuntimeException("Trvanie simulacie musi byt vacsie ako 0!");
		}

		this.trvanieSimulacie = trvanieSimulacie;
	}

	public void customPrepareSimulation()
	{
		this.statCakanieSystem = new Stat();
		this.statCakanieFrontStanok = new Stat();
		this.statRadFrontStanok = new Stat();
	}

	public void customPrepareReplication()
	{
		// Spustenie simulacie
		this.agentModel().odosliInicializacnuSpravu();
	}

	public void customReplicationFinished()
	{
		int aktualnaReplikacia = this.currentReplication() + 1;
		if (aktualnaReplikacia % 1000 == 0)
		{
			System.out.println("Aktualna replikacia: " + aktualnaReplikacia);
		}

		this.statCakanieFrontStanok.addSample(this.agentStanok().getPriemerneCakanieFrontStanok());
		this.statCakanieSystem.addSample(this.agentOkolie().getPriemerneCakanieSystem());
		this.statRadFrontStanok.addSample(this.agentStanok().getPriemernyRadFrontStanok());
	}

	public void customSimulationFinished()
	{
		System.out.println("Priemerne cakanie system: " + this.statCakanieSystem.mean());
		System.out.println("Priemerne cakanie front stanok: " + this.statCakanieFrontStanok.mean());
		System.out.println("Priemerne rad front stanok: " + this.statRadFrontStanok.mean());
	}

	public double getTrvanieSimulacie()
	{
		return this.trvanieSimulacie;
	}

	public MySimulation(double trvanieSimulacie)
	{
		init();

		this.customInit(trvanieSimulacie);
	}

	@Override
	public void prepareSimulation()
	{
		super.prepareSimulation();
		// Create global statistcis

		this.customPrepareSimulation();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Reset entities, queues, local statistics, etc...

		this.customPrepareReplication();
	}

	@Override
	public void replicationFinished()
	{
		// Collect local statistics into global, update UI, etc...
		super.replicationFinished();

		this.customReplicationFinished();
	}

	@Override
	public void simulationFinished()
	{
		// Dysplay simulation results
		super.simulationFinished();

		this.customSimulationFinished();
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		setAgentModel(new AgentModel(Id.agentModel, this, null));
		setAgentOkolie(new AgentOkolie(Id.agentOkolie, this, agentModel()));
		setAgentStanok(new AgentStanok(Id.agentStanok, this, agentModel()));
	}

	private AgentModel _agentModel;

public AgentModel agentModel()
	{ return _agentModel; }

	public void setAgentModel(AgentModel agentModel)
	{_agentModel = agentModel; }

	private AgentOkolie _agentOkolie;

public AgentOkolie agentOkolie()
	{ return _agentOkolie; }

	public void setAgentOkolie(AgentOkolie agentOkolie)
	{_agentOkolie = agentOkolie; }

	private AgentStanok _agentStanok;

public AgentStanok agentStanok()
	{ return _agentStanok; }

	public void setAgentStanok(AgentStanok agentStanok)
	{_agentStanok = agentStanok; }
	//meta! tag="end"
}