package controller.inputController;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utilities.InputCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This Class detect all the inputs of the user.
 * It also manages player tasks based on the inputs.
 */

public class InputControllerImpl implements InputController{

    private Map<KeyCode, Boolean> pressedKeys;
    private Map<InputCommands, KeyCode> commandKeys;
    private Map<InputCommands, Boolean> task;
    private Scene scene;

    // Flag used to implement fire logic.
    private boolean fireLabel;

    /**
     * Constructor.
     *
     * @param scene where this InputController detect keys.
     */
    public InputControllerImpl(Scene scene) {
        this.pressedKeys = new HashMap<>();
        this.commandKeys = new HashMap<>();
        this.task = new HashMap<>();
        this.scene = scene;
        this.fireLabel = true;
        this.initializeDefaultKeys();
        this.resetState();
        this.initializeListeners();
    }

    @Override
    public void changeScene(Scene scene) {
        this.scene = scene;
        this.initializeListeners();
    }

    @Override
    public void resetState() {
        List.of(InputCommands.values()).forEach(e -> this.task.put(e,false));
        List.of(KeyCode.values()).forEach(e -> this.pressedKeys.put(e, false));
    }

    /**
     * Initialize default keys.
     */
    private void initializeDefaultKeys() {
        this.commandKeys.put(InputCommands.UP, KeyCode.W);
        this.commandKeys.put(InputCommands.DOWN, KeyCode.S);
        this.commandKeys.put(InputCommands.LEFT, KeyCode.A);
        this.commandKeys.put(InputCommands.RIGHT, KeyCode.D);
        this.commandKeys.put(InputCommands.ATTACK, KeyCode.K);
    }

    @Override
    public KeyCode getKeyMapped(InputCommands command) {
        return null;
    }

    @Override
    public void addCommandKeys(KeyCode key, InputCommands command) {
        if (this.commandKeys.values().stream().anyMatch(e -> e.equals(key))) {
            InputCommands otherCommand = this.getKeyFromValue(this.commandKeys, key);
            KeyCode otherKey = this.commandKeys.get(command);
            this.commandKeys.replace(otherCommand, key, otherKey);
            this.commandKeys.replace(command, otherKey, key);
        } else {
            this.commandKeys.replace(command, key);
        }
    }

    /**
     * Initialize listeners used to check pressed and released keys.
     */
    private void initializeListeners() {
        this.scene.setOnKeyPressed(e -> {
            this.pressedKeys.put(e.getCode(), true);
        });
        this.scene.setOnKeyReleased(e -> {
            this.pressedKeys.put(e.getCode(), false);
        });
    }

    /**
     * Update state of tasks based on pressed keys.
     */
    private void updateKeysInfo() {
        //
        Set<KeyCode> activeKeys = this.pressedKeys.entrySet().stream().filter(entry -> entry.getValue())
                .map(entry -> entry.getKey()).collect(Collectors.toSet());
        Set<InputCommands> activeCommand = this.commandKeys.entrySet().stream()
                .filter(entry -> activeKeys.contains(entry.getValue())).map(entry -> entry.getKey())
                .collect(Collectors.toSet());
        // Set the state of task.
        this.task.keySet().stream().filter(key -> activeCommand.contains(key)).forEach(key -> this.task.put(key, true));
        this.task.keySet().stream().filter(key -> !activeCommand.contains(key))
                .forEach(key -> this.task.put(key, false));
    }

    /**
     * Return a Key from a map based on his mapped value.
     *
     * @param map
     * @param value
     *
     * @return
     */
    private <K, V> K getKeyFromValue(final Map<K, V> map, final V value) {
        return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(e -> e.getKey()).findFirst()
                .get();
    }

    @Override
    public boolean isKeyPressed(KeyCode key) {
        return this.pressedKeys.get(key);
    }

    @Override
    public boolean isTaskActive(InputCommands task) {
        return this.task.get(task);
    }

    @Override
    public void setKeyState(KeyCode key, boolean state) {
        this.pressedKeys.put(key, state);
    }

    @Override
    public void updatePlayerTasks() {
        this.updateKeysInfo();
        this.fireLogic();
        this.movementLogic();
    }

    private void movementLogic() {
        if (this.task.get(InputCommands.LEFT) && this.task.get(InputCommands.RIGHT)) {
            this.task.put(InputCommands.LEFT, false);
            this.task.put(InputCommands.RIGHT, false);
        }
    }

    /**
     * Manager of the fire logic.
     */
    private void fireLogic() {
        if (this.isTaskActive(InputCommands.ATTACK)) {
            if (this.fireLabel) {
                this.fireLabel = false;
            } else {
                this.task.put(InputCommands.ATTACK, false);
            }
        } else {
            this.fireLabel = true;
        }
    }
}
