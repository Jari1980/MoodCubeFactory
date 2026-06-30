import { useEffect, useState } from "react";
import CubeGrid from "../components/CubeGrid/CubeGrid";
import CubeDetails from "../components/CubeDetails/CubeDetails";
import ActionBar from "../components/Actions/ActionBar";
import { getCubes } from "../services/cubeApi";
import type { Cube } from "../types/Cube";
import "./Dashboard.css";

export default function Dashboard() {
  const [cubes, setCubes] = useState<Cube[]>([]);
  const [selectedCube, setSelectedCube] = useState<Cube | null>(null);

  useEffect(() => {
    loadCubes();
  }, []);

  async function loadCubes() {
    try {
      const data = await getCubes();
      setCubes(data);
    } catch (err) {
      console.error(err);
    }
  }

  return (
    <div className="dashboard">
      <header className="header">
        <h1>Mood Cube Factory</h1>
      </header>

      <main className="content">
        <section className="cube-area">
          <CubeGrid
            cubes={cubes}
            onSelect={setSelectedCube}
            selectedCube={selectedCube}
          />
        </section>

        <aside className="sidebar">
          <CubeDetails cube={selectedCube} />
        </aside>

        <div className="actions">
          <ActionBar />
        </div>

        <div className="log">Event log (coming soon)</div>
      </main>

    </div>
  );
}
