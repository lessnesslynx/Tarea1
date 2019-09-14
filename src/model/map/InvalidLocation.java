package model.map;

/**
 * This class represents an empty or invalid location on the game's map.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class InvalidLocation extends Location {

  /**
   * Creates an empty location on the game's map
   */
  InvalidLocation() {
    super(-1, -1);
  }

  /**Doesn't do anything, by design.
   *
   * @param neighbour neighbor of the location to (not) be set as adjacent
   */
  @Override
  public void addNeighbour(final Location neighbour) {
  }

  /**Doesn't do anything, by design.
   *
   * @param location neighbor of the location to (not) be added as neighbour
   */
  @Override
  protected void addTo(final Location location) {
  }
}
