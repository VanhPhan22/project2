package project2.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project2.dto.UserAccountResponse;
import project2.entity.HorseOwner;
import project2.entity.Jockey;
import project2.entity.RaceReferee;
import project2.entity.Spectator;
import project2.enums.UserAccountType;
import project2.exception.ResourceNotFoundException;
import project2.repository.HorseOwnerRepo;
import project2.repository.JockeyRepo;
import project2.repository.RaceRefereeRepo;
import project2.repository.SpectatorRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountManagementService {

    private final HorseOwnerRepo horseOwnerRepo;
    private final JockeyRepo jockeyRepo;
    private final RaceRefereeRepo raceRefereeRepo;
    private final SpectatorRepo spectatorRepo;

    public UserAccountManagementService(
            HorseOwnerRepo horseOwnerRepo,
            JockeyRepo jockeyRepo,
            RaceRefereeRepo raceRefereeRepo,
            SpectatorRepo spectatorRepo
    ) {
        this.horseOwnerRepo = horseOwnerRepo;
        this.jockeyRepo = jockeyRepo;
        this.raceRefereeRepo = raceRefereeRepo;
        this.spectatorRepo = spectatorRepo;
    }

    @Transactional(readOnly = true)
    public List<UserAccountResponse> findAll(UserAccountType accountType) {
        if (accountType != null) {
            return findAllByType(accountType);
        }

        List<UserAccountResponse> accounts = new ArrayList<>();
        for (UserAccountType type : UserAccountType.values()) {
            accounts.addAll(findAllByType(type));
        }
        return accounts;
    }

    @Transactional(readOnly = true)
    public UserAccountResponse findById(UserAccountType accountType, Integer id) {
        return switch (accountType) {
            case HORSE_OWNER -> toResponse(horseOwnerRepo.findById(id)
                    .orElseThrow(() -> notFound(accountType, id)));
            case JOCKEY -> toResponse(jockeyRepo.findById(id)
                    .orElseThrow(() -> notFound(accountType, id)));
            case RACE_REFEREE -> toResponse(raceRefereeRepo.findById(id)
                    .orElseThrow(() -> notFound(accountType, id)));
            case SPECTATOR -> toResponse(spectatorRepo.findById(id)
                    .orElseThrow(() -> notFound(accountType, id)));
        };
    }

    @Transactional
    public void delete(UserAccountType accountType, Integer id) {
        JpaRepository<?, Integer> repository = repositoryFor(accountType);
        if (!repository.existsById(id)) {
            throw notFound(accountType, id);
        }
        repository.deleteById(id);
    }

    private List<UserAccountResponse> findAllByType(UserAccountType accountType) {
        return switch (accountType) {
            case HORSE_OWNER -> horseOwnerRepo.findAll().stream().map(this::toResponse).toList();
            case JOCKEY -> jockeyRepo.findAll().stream().map(this::toResponse).toList();
            case RACE_REFEREE -> raceRefereeRepo.findAll().stream().map(this::toResponse).toList();
            case SPECTATOR -> spectatorRepo.findAll().stream().map(this::toResponse).toList();
        };
    }

    private JpaRepository<?, Integer> repositoryFor(UserAccountType accountType) {
        return switch (accountType) {
            case HORSE_OWNER -> horseOwnerRepo;
            case JOCKEY -> jockeyRepo;
            case RACE_REFEREE -> raceRefereeRepo;
            case SPECTATOR -> spectatorRepo;
        };
    }

    private ResourceNotFoundException notFound(UserAccountType accountType, Integer id) {
        return new ResourceNotFoundException(accountType + " account with ID " + id + " was not found");
    }

    private UserAccountResponse toResponse(HorseOwner account) {
        return new UserAccountResponse(
                account.getId(), UserAccountType.HORSE_OWNER.name(), account.getUserName(),
                account.getFullName(), account.getEmail(), account.getPhone()
        );
    }

    private UserAccountResponse toResponse(Jockey account) {
        return new UserAccountResponse(
                account.getId(), UserAccountType.JOCKEY.name(), account.getUserName(),
                account.getFullName(), null, account.getPhone()
        );
    }

    private UserAccountResponse toResponse(RaceReferee account) {
        return new UserAccountResponse(
                account.getId(), UserAccountType.RACE_REFEREE.name(), account.getUsername(),
                account.getFullName(), account.getEmail(), account.getPhone()
        );
    }

    private UserAccountResponse toResponse(Spectator account) {
        return new UserAccountResponse(
                account.getId(), UserAccountType.SPECTATOR.name(), account.getUserName(),
                null, account.getEmail(), null
        );
    }
}
