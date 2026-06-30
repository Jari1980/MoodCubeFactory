import type { Cube } from "../../types/Cube";

interface Props {
  cube: Cube | null;
}

export default function CubeDetails({ cube }: Props) {
  if (!cube) {
    return <div>Select a cube</div>;
  }

  return (
    <div>
      <h2>Cube #{cube.id}</h2>

      <p>
        <b>Color:</b> {cube.color}
      </p>
      <p>
        <b>Mood:</b> {cube.mood}
      </p>
      <p>
        <b>Energy:</b> {cube.energy}
      </p>
      <p>
        <b>Updated:</b> {cube.updatedAt}
      </p>
    </div>
  );
}
