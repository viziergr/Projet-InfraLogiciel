package com.timefusion.service;

import com.timefusion.dao.TeamDao;
import com.timefusion.model.Team;
import java.util.List;
import java.util.Optional;

public class TeamService {

  private TeamDao teamDao;

  public TeamService(TeamDao teamDao) {
    this.teamDao = teamDao;
  }

  public List<Team> getAllTeams() {
    return teamDao.getAllTeams();
  }

  public Optional<Team> getTeamById(Long id) {
    return teamDao.getTeamById(id);
  }

  public void saveTeam(Team team) {
    teamDao.saveTeam(team);
  }

  public void updateTeam(Team team) {
    teamDao.updateTeam(team);
  }

  public void deleteTeam(Long id) {
    teamDao.deleteTeam(id);
  }
}
