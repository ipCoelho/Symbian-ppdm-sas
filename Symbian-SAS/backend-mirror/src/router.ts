import controller from "@controllers/Controller";

import { Router } from "express";

const router = Router(); 

router.get("/health", (_, res) => res.status(200).json({ status: 200, health: "Integrity OK" }));
router.use(controller);

export default router;
