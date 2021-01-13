import { RecomendedWineResponse } from "./recomended_wine_response";

export interface RecomendedWineResponseContainer {
    recommendedWines: RecomendedWineResponse[];
    totalFound: number;
}
  