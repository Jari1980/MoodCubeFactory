import type { Cube } from "../../types/Cube";
import "./CubeGrid.css";

interface Props {
    cubes: Cube[];
    selectedCube: Cube | null;
    onSelect: (cube: Cube) => void;
}

export default function CubeGrid({
    cubes,
    selectedCube,
    onSelect
}: Props) {

    return (
    <div className="cube-grid">
        {cubes.map((cube) => {

            const isSelected = selectedCube?.id === cube.id;

            const size = 20 + cube.energy * 0.25; 
            // 20px → 45px range

            return (
                <div key={cube.id} className="cube-cell">
                    <button
                        className={`cube ${isSelected ? "selected" : ""}`}
                        style={{
                            backgroundColor: cube.color,
                            width: size,
                            height: size
                        }}
                        onClick={() => onSelect(cube)}
                    >
                        {cube.energy}
                    </button>
                </div>
            );
        })}
    </div>
);
}