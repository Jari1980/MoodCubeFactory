import type { Cube } from "../types/Cube";

const API_URL = import.meta.env.VITE_API_URL;

export async function getCubes(): Promise<Cube[]> {
    const response = await fetch(`${API_URL}/cubes`);

    if (!response.ok) {
        throw new Error("Failed to fetch cubes.");
    }

    return response.json();
}