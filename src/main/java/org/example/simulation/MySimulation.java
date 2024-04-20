package org.example.simulation;

import OSPABA.*;
import org.example.agents.*;

public class MySimulation extends Simulation
{
	private double trvanieSimulacie;

	public void customInit(double trvanieSimulacie)
	{
		if (trvanieSimulacie <= 0)
		{
			throw new RuntimeException("Trvanie simulacie musi byt vacsie ako 0!");
		}

		this.trvanieSimulacie = trvanieSimulacie;
	}

	public void customPrepareReplication()
	{
		// Spustenie simulacie
		this.agentModel().odosliInicializacnuSpravu();
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
	}

	@Override
	public void simulationFinished()
	{
		// Dysplay simulation results
		super.simulationFinished();
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