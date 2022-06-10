import { PrismaClient, User } from "@prisma/client";
import { Request, Response, Router } from "express";

const router = Router();
const prisma = new PrismaClient();

router.get("/user", async (req: Request, res: Response) => {
  const allUsers: User[] = await prisma.user.findMany();

  return res.status(200).json({
    users: allUsers
  });
});

router.post("/user", async (req: Request, res: Response) => {
  console.info("body: \n", req.body);
  const user: User = req.body;

  const newUser = await prisma.user.create({
    data: { ...user }
  })

  if (newUser != null) {
    return res.status(200).json({
      message: `User ${user.name} created successfully`,
      user: newUser
    });
  } else {
    return res.status(500);
  }
});

router.delete("/user", async (req: Request, res: Response) => {
  console.log("query.id: ", req.query.id);
  const idUser = String(req.query.id);

  if (req.query.id != null) {
    const userVerify = await prisma.user.findUnique({
      where: { idUser: idUser }
    })

    if (userVerify != null) {
      const deleteUser = await prisma.user.delete({
        where: { idUser: idUser }
      });

      return res.status(200).json({
        message: `User ${deleteUser.name} exclude successfully`,
        user: deleteUser
      });
    } else {
      return res.status(404).json({
        message: `User with id ${req.query.id} not found`
      });
    }
  } else {
    const deleteAll = await prisma.user.deleteMany();

    return res.status(200).json({
      message: `All users exclude successfully`,
      user: deleteAll
    });
  }
});

export default router;

